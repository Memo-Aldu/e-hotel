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
                <div className="flexGrow">
                    <Link to="/">Home</Link>
                    <Link to="/lounge">Home</Link>
                </div>
            </section>
        </>
    )
}

export default addCustomer
