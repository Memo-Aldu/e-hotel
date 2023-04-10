import React from 'react'
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';
import styles from './Navbar.css';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

export const AppNavbar = () => {
    const [expanded, setExpanded] = React.useState(false);
    const [show, setShow] = React.useState(false);
    const overrideToggle = () => {
        setExpanded(!expanded);
    };
    return (
        <div className=''>
            <Navbar className={expanded ? "navbar-custom" : "bg-dark"} collapseOnSelect expand="sm" variant="dark" expanded={expanded} onToggle={overrideToggle}>
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
                    <Nav.Link href="/login" className=''>
                        <i className="fas fa-user"></i>
                        <p className="d-inline ms-2">Login</p>
                    </Nav.Link>
                    <Nav.Link eventKey={2} href="/register">
                        <i className="fas fa-user-plus"></i>
                        <p className="d-inline ms-2">Register</p>
                    </Nav.Link>
                </Nav>
                </Navbar.Collapse>
            </Container>
            </Navbar>
        </div>);
}
