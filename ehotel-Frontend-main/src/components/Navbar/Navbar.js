import React from 'react'
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import { useNavigate } from 'react-router-dom';
import {selectCurrentUser } from '../../features/auth/authSlice';
import {useSelector, useDispatch} from 'react-redux';
import { logOut } from '../../features/auth/authSlice'
import styles from './Navbar.css';

export const AppNavbar = () => {
    const [expanded, setExpanded] = React.useState(false);
    const [show, setShow] = React.useState(false);
    const user = useSelector(selectCurrentUser)
    const dispatch = useDispatch()
    const [showLogin, setShowLogin] = React.useState(user == null ? true : false);
    const navigate = useNavigate();
    const overrideToggle = () => {
        setExpanded(!expanded);
    };

    const handleLoginLogout = () => {
        setShowLogin(!showLogin);
        if (showLogin) {
            navigate('/login');
        } else {
            dispatch(logOut());
            navigate('/');
        }
    }
    const handleRegister = () => {
        console.log('register');
        navigate('/register');
    }
    return (
        <div className='' style={{zIndex: 1000, width: '100%'}}>
            <Navbar sticky='top' className={expanded ? "navbar-custom" : "bg-dark"} collapseOnSelect expand="sm" variant="dark" expanded={expanded} onToggle={overrideToggle}>
            <Container className="mx-auto my-1">
                <Navbar.Brand href="/">EHotel</Navbar.Brand>
                <Navbar.Toggle aria-controls="responsive-navbar-nav">
                    <span className=""><i className={expanded ? "fa-solid fa-x" : "fa-solid fa-bars"}></i></span>
                </Navbar.Toggle>
                <Navbar.Collapse id="responsive-navbar-nav">
                <Nav className="me-auto">
                    <Nav.Link href="/">Home</Nav.Link>
                    <Nav.Link href="/">About us</Nav.Link>
                    <Nav.Link href="/">Destination</Nav.Link>
                </Nav>
                <Nav>
                    <Nav.Link onClick={handleLoginLogout} className=''>
                        <i className={showLogin ? "fas fa-user" : "fas fa-arrow-right-from-bracket" }></i>
                        <p className="d-inline ms-2">{showLogin ? 'Login' : 'Logout'}</p>
                    </Nav.Link>
                    {
                        showLogin ? 
                        <Nav.Link eventKey={2} onClick={handleRegister}>
                            <i className="fas fa-user-plus"></i>
                            <p className="d-inline ms-2">Register</p>
                        </Nav.Link>
                        : null
                    }
                </Nav>
                </Navbar.Collapse>
            </Container>
            </Navbar>
        </div>);
}