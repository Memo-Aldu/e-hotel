import {useState, useEffect, useRef } from 'react'
import { useNavigate, useLocation } from 'react-router-dom'
import { useAddCustomerMutation } from '../../features/customer/customerApiSlice'
import { Link } from 'react-router-dom'

export const CustomerForm = () => {
    const [errMsg, setErrMsg] = useState('')
    const errRef = useRef();
    const navigate = useNavigate()

    const nameRef = useRef();
    const [name, setName] = useState('')

    const middlenameRef = useRef();
    const [middlename, setMiddleName] = useState('')

    const lastnameRef = useRef();
    const [lastname, setLastName] = useState('')

    const DOBRef = useRef();
    const [DOB, setDOB] = useState('')

    const registrationRef = useRef();
    const [registration, setRegistration] = useState('')

    const addressRef = useRef();
    const [address, setAddress] = useState('')

    const emailRef = useRef();
    const [email, setEmail] = useState('')

    const phoneNumberRef = useRef();
    const [phoneNumber, setPhoneNumber] = useState('')

    const NASRef = useRef();
    const [NAS, setNAS] = useState('')

    const customer = {
        "NAS" : NAS,
        "email" : email,
        "phoneNumber" : phoneNumber,
        "firstName" : name,
        "middleName" :middlename,
        "lastName" : lastname,
        "address" : address,
        "dateOfBirth" : DOB,
        "registrationDate" : registration
    }

    const [addCustomer, { isLoading }] = useAddCustomerMutation()
    useEffect(() => {
        emailRef.current.focus() // focus on email input
    }, []);

    useEffect(() => {
        setErrMsg('') // clear error message
    }, customer);

    const handleSubmit = async (e) => {
        e.preventDefault()
        try {
            await addCustomer(customer).unwrap()
            setName('');
            setMiddleName('');
            setLastName('');
            setDOB('');
            setRegistration('');
            setNAS('');
            setAddress('');
            setEmail('');
            setPhoneNumber('');
            console.log('New customer')
            navigate('/lounge')
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
                setErrMsg('Add Failed')
            }
            errRef.current.focus() // focus on error message, screen reader will read it
        }
    }
    const handleNameInput = (e) => {setName(e.target.value)}
    const handleMiddleNameInput = (e) => {setMiddleName(e.target.value)}
    const handleLastNameInput = (e) => {setLastName(e.target.value)}
    const handleDOBInput = (e) => {setDOB(e.target.value)}
    const handleRegistrationInput = (e) => {setRegistration(e.target.value)}
    const handleAddressInput = (e) => {setAddress(e.target.value)}
    const handleEmailInput = (e) => {setEmail(e.target.value)}
    const handlePhoneNumberInput = (e) => {setPhoneNumber(e.target.value)}
    const handleNASInput = (e) => {setNAS(e.target.value)}

    const content  = isLoading ? <h1>Loding...</h1> : (
    <section>
            <p ref={errRef} className={errMsg ? "errmsg" : "offscreen"} aria-live="assertive">{errMsg}</p>
            <h1>Add Customer</h1>

            
            <form onSubmit={handleSubmit} className="ui form">
                <div className="field">
                    <div className="two fields">

                        <div className="field">
                            <div>
                                <label htmlFor="name">Name:</label>
                                <input
                                    type="text"
                                    id="name"
                                    ref={nameRef}
                                    autoComplete="off"
                                    onChange={handleNameInput}
                                    value={name}
                                    required
                                />
                            </div>
                            </div>
                            <div className="field">
                            <div>
                                <label htmlFor="middlename">MiddleName:</label>
                                <input
                                    type="text"
                                    id="middlename"
                                    ref={middlenameRef}
                                    autoComplete="off"
                                    onChange={handleMiddleNameInput}
                                    value={middlename}
                                    required
                                />
                            </div>
                            </div>
                            <div className="field">
                            <div>
                                <label htmlFor="lastname">LastName:</label>
                                <input
                                    type="text"
                                    id="lastname"
                                    ref={lastnameRef}
                                    autoComplete="off"
                                    onChange={handleLastNameInput}
                                    value={name}
                                    required
                                />
                            </div>
                            </div>
                            <div className="field">
                            <div>
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
                            </div>
                        </div>
                    </div>
                    <div className="field">
                            <div>
                                <label htmlFor="address">Address:</label>
                                <input
                                    type="text"
                                    id="address"
                                    ref={addressRef}
                                    autoComplete="off"
                                    onChange={handleAddressInput}
                                    value={address}
                                    required
                                />
                            </div>
                        </div>

     
                        <div className="field">
                            <div>
                                <label htmlFor="DOB">Date of Birth:</label>
                                <input
                                    type="date"
                                    id="DOB"
                                    ref={DOBRef}
                                    autoComplete="off"
                                    onChange={handleDOBInput}
                                    value={DOB}
                                    required
                                />
                            </div>
                        </div>
                        <div className="field">
                            <div>
                                <label htmlFor="registration">Registration:</label>
                                <input
                                    type="date"
                                    id="registration"
                                    ref={registrationRef}
                                    autoComplete="off"
                                    onChange={handleRegistrationInput}
                                    value={registration}
                                    required
                                />
                            </div>
                        </div>
  
                        <div className="field">
                            <div>
                                <label htmlFor="NAS">NAS:</label>
                                <input
                                    type="bigint"
                                    id="NAS"
                                    ref={NASRef}
                                    autoComplete="off"
                                    onChange={handleNASInput}
                                    value={NAS}
                                    required
                                />
                            </div>
                        </div>
                    
                    
                    <div className="two fields">
                        <div className="field">
                            <div>
                                <label htmlFor="phoneNumber">Phone Number:</label>
                                <input
                                    type="text"
                                    id="phoneNumber"
                                    ref={phoneNumberRef}
                                    autoComplete="off"
                                    onChange={handlePhoneNumberInput}
                                    value={phoneNumber}
                                    required
                                />
                            </div>
                            </div>
  
                        </div>    
                </div>
                <button>Add</button>
            </form>
            <p>
                Return
                <span className="line">
                    <Link to="/">Back</Link>
                </span>
            </p>
        </section>)
  return content;
}

export default CustomerForm
