import { Link } from "react-router-dom"
import { AppNavbar } from "../../components/Navbar/Navbar"

const Lounge = () => {
    return (
        <>
            <AppNavbar/>
            <section>
                <h1>The Lounge</h1>
                <br />
                <p>Employee and Editors can hang out here.</p>
                    <h3>Add elements</h3>
                <div className="btn-group">
                <Link  className="btn btn-primary m-3 pl-1" to="/addHotel">Hotel</Link>  
                <Link  className="btn btn-primary m-3 pl-1" to="/addStay">Stay</Link>
                <Link  className="btn btn-primary m-3 pl-1" to="/addEmployee">Employee</Link>
                <Link  className="btn btn-primary m-3 pl-1" to="/addCustomer">Customer</Link>
                <Link  className="btn btn-primary m-3 pl-1" to="/addRoom">Room</Link>
                    </div>
                <div className="flexGrow">
                    <Link to="/">Home</Link>
                </div>
            </section>
        </>
    )
}

export default Lounge
