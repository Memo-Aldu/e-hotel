import { useNavigate } from "react-router-dom"
import { AppNavbar } from "../../components/Navbar/Navbar";

const Unauthorized = () => {
    const navigate = useNavigate();

    const goBack = () => navigate(-1);

    return (
        <>
            <AppNavbar/>
            <section>
            <h1>Unauthorized</h1>
            <br />
            <p>You do not have access to the requested page.</p>
            <div className="flexGrow">
                <button onClick={goBack}>Go Back</button>
            </div>
        </section>
        </>

    )
}

export default Unauthorized
