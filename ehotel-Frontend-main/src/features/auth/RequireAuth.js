import { useLocation, Outlet, Navigate} from "react-router-dom"
import {useSelector} from 'react-redux'
import { selectCurrentToken } from "./authSlice"


const RequireAuth = ({ allowedRoles }) => {
    const {user, loading } = useSelector(state => state.auth);
    console.log("user", user, "loading", loading)
    const token = useSelector(selectCurrentToken)
    const location = useLocation()

    if (loading) {
        return <p className="container">Checking auth..</p>;
    }
    
    const userHasRequiredRole = user && allowedRoles.includes(user.userRole) ? true : false;

    if (!userHasRequiredRole && !loading && user) {
        console.log("not authorized")
        return <Navigate to="/unauthorized" state={{ from: location }} replace />; // build your won access denied page (sth like 404)
    }
    console.log("authenticated")
    
    return (
        token ? <Outlet /> 
        : <Navigate to="/login" state={{from: location}} replace />
    )
}

export default RequireAuth

