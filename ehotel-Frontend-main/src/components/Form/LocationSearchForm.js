import React, { useEffect } from 'react'
import styles from './Form.css'
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import Container from 'react-bootstrap/Container';
import { Row, Col } from 'react-bootstrap';
import { wait } from '@testing-library/user-event/dist/utils';


const LocationSearchForm = () => {
    const [searchInput, setSearchInput] = React.useState('');
    const [checkIn, setCheckIn] = React.useState('');
    const [checkOut, setCheckOut] = React.useState('');
    const [guestAdults, setGuestAdults] = React.useState('');
    const [guestChildren, setGuestChildren] = React.useState('');
    const [validated, setValidated] = React.useState(false);

    const handleChangeSearch = (e) => {
        setSearchInput(e.target.value);
        console.log('searchInput', searchInput);
    };
    const handleChangeCheckIn = (e) => {
        let checkIn = new Date(e.target.value).toISOString().split('T')[0];
        setCheckIn(checkIn);
    };
    const handleChangeCheckOut = (e) => {
        let checkout = new Date(e.target.value).toISOString().split('T')[0];
        setCheckOut(checkout);
    };
    const handleChangeGuestAdults = (e) => {
        setGuestAdults(e.target.value);
    };
    const handleChangeGuestChildren = (e) => {
        setGuestChildren(e.target.value);
    };

    const handleSubmit = async (e) => {
        const form = e.currentTarget;
        if (form.checkValidity() === false) {
            e.preventDefault();
            e.stopPropagation();
        }
        console.log('form', form);
        wait(1000)
        setValidated(true);
        const data = {
            searchInput,
            checkIn,
            checkOut,
            guestAdults,
            guestChildren
        };
        console.info('data', data);
    };

  return (
    <Form className="slide-form" noValidate validated={validated} onSubmit={handleSubmit}>
    <Container fluid="md">
        <h2>Enjoy Your Holiday</h2>
        <span>Search and Book Hotel.</span>
        <Row className="">
            <Col>
                <Form.Group className="" controlId="formSearch" >
                    <Form.Control type="search" placeholder="Search here" onChange={handleChangeSearch} required/>
                </Form.Group>
            </Col>
        </Row>
        <Row >
            <Col className="col-lg-6 col-12">
                <Form.Group className="" controlId="formCheckIn">
                        <Form.Control type="date" placeholder="Check In" onChange={handleChangeCheckIn} required/>
                </Form.Group>
            </Col>
            <Col className="col-lg-6 col-12">
                <Form.Group className="" controlId="formCheckOut">
                            <Form.Control type="date" placeholder="Check Out" onChange={handleChangeCheckOut} required/>
                </Form.Group>
            </Col>
        </Row>
        <Row className="">
            <Col className="col-lg-6  col-12">
                <Form.Group className="" controlId="formAdult">
                        <Form.Control type="number" placeholder="Adult(s)" onChange={handleChangeGuestAdults}/>
                </Form.Group>
            </Col>
            <Col className="col-lg-6 col-12">
                <Form.Group className="" controlId="formChildren">
                            <Form.Control type="number" placeholder="Children" onChange={handleChangeGuestChildren}/>
                </Form.Group>
            </Col>
        </Row>
        <Row>
            <Col className="d-grid col-12">
                <Button variant="primary" type="submit" color='' className='submit mt-3'>Submit</Button>
            </Col>
        </Row>
      </Container>
    </Form>);
}

export default LocationSearchForm