import {useState, useEffect, useRef } from 'react'
import { useNavigate, useLocation, Link } from 'react-router-dom'
import {useDispatch, useSelector} from 'react-redux'
import {setCredentials} from '../../features/auth/authSlice'
import { useLoginMutation } from '../../features/auth/authApiSlice'

const LoginForm = () => {
    const emailRef = useRef();
    const errRef = useRef();
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const [errMsg, setErrMsg] = useState('')
    const navigate = useNavigate()

    const [login, { isLoading, error }] = useLoginMutation()
    const dispatch = useDispatch()
    useEffect(() => {
        emailRef.current.focus() // focus on email input
    }, []);

    useEffect(() => {
        setErrMsg('') // clear error message
    }, [email, password]);

    const handleSubmit = async (e) => {
        e.preventDefault()
        try {
            const result = await login({email, password}).unwrap()
            dispatch(setCredentials({user: result.data.user, accessToken: result.data.access_token, 
                refreshToken: result.data.refresh_token}))
            setEmail('');
            setPassword('');
            if(result.data.user.userRole === 'ROLE_EMPLOYEE') {
                console.log('employee')
                navigate('/employee')
            } else if(result.data.user.userRole === 'ROLE_CUSTOMER') { //APP_USER 
                navigate('/') // navigate to customer page
            } else {
                navigate('/') // navigate to home page
            }
        } catch (err) {
            if (!err?.status) {
                setErrMsg('No Server Response')
            } else if (err.status === 400) {
                setErrMsg('Missing email or Password')
            } else if (err.status === 403) {
                setErrMsg('Unauthorized')
            } else if(err.status === 404) {
                setErrMsg('User Not Found')
            } 
            else {
                setErrMsg('Login Failed')
            }
            errRef.current.focus() // focus on error message, screen reader will read it
        }
    }
    const handleEmailInput = (e) => {setEmail(e.target.value)}
    const handlePasswordInput = (e) => {setPassword(e.target.value)}

    const content  = isLoading ? <h1>Loding...</h1> : (
    <section>
            <p ref={errRef} className={errMsg ? "errmsg" : "offscreen"} aria-live="assertive">{errMsg}</p>
            <h1>Sign In</h1>
            <form onSubmit={handleSubmit}>
                <label htmlFor="email">Email:</label>
                <input
                    type="text"
                    id="email"
                    ref={emailRef}
                    autoComplete="off"
                    onChange={handleEmailInput}
                    value={email}
                    required
                />

                <label htmlFor="password">Password:</label>
                <input
                    type="password"
                    id="password"
                    onChange={handlePasswordInput}
                    value={password}
                    required
                />
                <button>Sign In</button>
            </form>
            <p>
                Create an account
                <span className="line">
                    <Link to="/register">Sign Up</Link>
                </span>
            </p>
        </section>)
  return content;
}

export default LoginForm