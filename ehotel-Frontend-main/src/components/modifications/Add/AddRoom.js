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
            </section>
        </>
    )
}

export default AddRoom
