import { Link } from "react-router-dom"
import { Routes, Route } from 'react-router-dom';
import { AppNavbar } from "../../Navbar/Navbar"
import { useNavigate } from "react-router-dom";

import { HotelForm } from "../../Form/HotelForm"

export const AddHotel = () => {
    const navigate = useNavigate();
    return (
        
        <>

            <AppNavbar/>
            <section>
                <br />
                <HotelForm/>
                <div className="flexGrow">
                    <button className="btn btn-primary m-3 pl-1" onClick={() => navigate(-1)}>Back</button>
                </div>
            </section>
        </>
    )
}

export default AddHotel
