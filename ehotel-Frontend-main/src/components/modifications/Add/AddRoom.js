import { Link } from "react-router-dom"
import { Routes, Route } from 'react-router-dom';
import { AppNavbar } from "../../Navbar/Navbar"

import { RoomForm } from "../../Form/RoomForm"

const AddRoom = () => {
    return (
        
        <>

            <AppNavbar/>
            <section>
                <br />
                <RoomForm/>
                <div className="flexGrow">
                    <Link to="/">Home</Link>
                    <Link to="/lounge">Home</Link>
                </div>
            </section>
        </>
    )
}

export default AddRoom
