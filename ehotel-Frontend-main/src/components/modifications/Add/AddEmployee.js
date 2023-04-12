import { Link } from "react-router-dom"
import { Routes, Route } from 'react-router-dom';
import { AppNavbar } from "../../Navbar/Navbar"

import { EmployeeForm } from "../../Form/EmployeeForm"

export const addEmployee = () => {
    return (
        
        <>

            <AppNavbar/>
            <section>
                <br />
                <EmployeeForm/>
            </section>
        </>
    )
}

export default addEmployee
