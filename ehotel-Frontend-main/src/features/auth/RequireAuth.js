import { useLocation, Outlet, Navigate} from "react-router-dom"
import {useSelector} from 'react-redux'
import { selectCurrentToken, selectCurrentUser } from "./authSlice"


const RequireAuth = ({ allowedRoles }) => {
    const user = useSelector(selectCurrentUser)
    const token = useSelector(selectCurrentToken)
    const location = useLocation()

    
    const userHasRequiredRole = user && allowedRoles.includes(user.userRole) ? true : false;

    if (!userHasRequiredRole) {
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

