import React from 'react'
import {useSelector} from 'react-redux'
import { AppNavbar } from "../../components/Navbar/Navbar";
import AppCarousel from "../../components/Carousel/Carousel";
import AppSpinner from '../../components/spinner/Spinner'
import { selectCurrentLoading } from "../../features/app/loadSlice";
import LoginForm from '../../components/Form/LoginForm';

const LoginPage = () => {
    const loading = useSelector(selectCurrentLoading)
    return (
        <>
            <AppNavbar/>
            <LoginForm/>
            <div className={loading ? 'loader' : 'd-none'}>
                <AppSpinner/>  
            </div>
        </>
    )
}

export default LoginPage