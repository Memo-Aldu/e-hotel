import { Link } from "react-router-dom"
import { Routes, Route } from 'react-router-dom';
import { AppNavbar } from "../../Navbar/Navbar"

import { CustomerForm } from "../../Form/CustomerForm"

export const addCustomer = () => {
    return (
        
        <>

            <AppNavbar/>
            <section>
                <br />
                <CustomerForm/>
            </section>
        </>
    )
}

export default addCustomer
