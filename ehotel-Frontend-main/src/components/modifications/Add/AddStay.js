import { Link } from "react-router-dom"
import { Routes, Route } from 'react-router-dom';
import { AppNavbar } from "../../Navbar/Navbar"
import { useNavigate } from "react-router-dom";
import { StayForm } from "../../Form/StayForm"

const AddStay = () => {
    const navigate = useNavigate();
    return (
        
        <>

            <AppNavbar/>
            <section>
                <br />
                <StayForm/>
                <div className="flexGrow">
                    <div className="flexGrow">
                        <button className="btn btn-primary m-3 pl-1" onClick={() => navigate(-1)}>Back</button>
                    </div>
                </div>
            </section>
        </>
    )
}

export default AddStay
