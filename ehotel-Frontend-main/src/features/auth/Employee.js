import { Link } from "react-router-dom";
import { selectCurrentToken, selectCurrentUser } from "./authSlice";
import { useSelector } from "react-redux";

const Employee = () => {
    const token = useSelector(selectCurrentToken)
    const user = useSelector(selectCurrentUser)

    const welcome = user ? `Welcome ${user.email}` : "Welcome!"
    const tokenAbbr = token ? `${token.slice(0, 9)}...` : "No token"
    const content =  (
        <section>
            <h1>{welcome}</h1>
            <p>Token : {tokenAbbr}</p>
            <div className="flexGrow">
                <Link to="/">Home</Link>
            </div>
        </section>
    )

    return content;
}

export default Employee
