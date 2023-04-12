import { Link } from "react-router-dom"
import { Routes, Route } from 'react-router-dom';
import { AppNavbar } from "../../Navbar/Navbar"

import { HotelForm } from "../../Form/HotelForm"

export const AddHotel = () => {
    return (
        
        <>

            <AppNavbar/>
            <section>
                <br />
                <HotelForm/>
                <div className="flexGrow">
                    <Link to="/">Home</Link>
                    <Link to="/lounge">lounge</Link>
                </div>
            </section>
        </>
    )
}

export default AddHotel
