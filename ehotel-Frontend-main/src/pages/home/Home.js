import { useNavigate, Link } from "react-router-dom";
import { useContext } from "react";
import {useDispatch} from 'react-redux'
import {logOut} from './../../features/auth/authSlice'
import { AppNavbar } from "../../components/Navbar/Navbar";

const Home = () => {
    const navigate = useNavigate();
    const dispatch = useDispatch();

    const logout = async () => {
        dispatch(logOut())
        navigate('/login')
    }

    return (
        <>
            <AppNavbar/>
            <section>
                <h1>Home</h1>
                <br />
                <p>You are logged in!</p>
                <br />
                <Link to="/employee">Go to the Employee page</Link>
                <br />
                <Link to="/lounge">Go to the Lounge</Link>
                <br />
                <Link to="/linkpage">Go to the link page</Link>
                <div className="flexGrow">
                    <button onClick={logout}>Sign Out</button>
                </div>
            </section>
        </>
       
    )
}

export default Home
