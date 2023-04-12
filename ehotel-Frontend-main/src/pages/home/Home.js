import {Link } from "react-router-dom";
import {useSelector} from 'react-redux'
import { AppNavbar } from "../../components/Navbar/Navbar";
import AppCarousel from "../../components/Carousel/Carousel";
import LocationSearchForm from "../../components/Form/LocationSearchForm";
import styles from './Home.css'
import AppSpinner from '../../components/spinner/Spinner'
import { selectCurrentLoading } from "../../features/app/loadSlice";

const Home = () => {
    const loading = useSelector(selectCurrentLoading)

    return (
        <>
            <AppNavbar/>
            <AppCarousel/>
            <div className='search-form'>
                <LocationSearchForm className='d-flex color-overlay justify-content-center align-items-center' />
            </div>
            <div className={loading ? 'loader' : 'd-none'}>
                <AppSpinner/>  
            </div>

            <section>
                <Link to="/employee">Go to the Employee page</Link>
                <br />
                <Link to="/lounge">Go to the Lounge</Link>
                <br />
                <Link to="/linkpage">Go to the link page</Link>
            </section>
        </>
       
    )
}

export default Home
