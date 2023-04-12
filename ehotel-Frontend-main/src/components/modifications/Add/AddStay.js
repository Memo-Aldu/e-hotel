import { Link } from "react-router-dom"
import { Routes, Route } from 'react-router-dom';
import { AppNavbar } from "../../Navbar/Navbar"

import { StayForm } from "../../Form/StayForm"

const AddStay = () => {
    return (
        
        <>

            <AppNavbar/>
            <section>
                <br />
                <StayForm/>
                <div className="flexGrow">
                    <Link to="/">Home</Link>
                    <Link to="/lounge">Home</Link>
                </div>
            </section>
        </>
    )
}

export default AddStay
