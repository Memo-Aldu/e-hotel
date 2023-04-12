import React, { useEffect, useState } from 'react'
import styles from './Form.css'
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import Container from 'react-bootstrap/Container';
import { useSearchHotelMutation } from '../../features/hotel/hotelApiSlice';
import { useNavigate, Link } from 'react-router-dom'
import { useDispatch, useSelector } from 'react-redux';
import { setLoading } from '../../features/app/loadSlice';



const LocationSearchForm = (props) => {
    const passedSearchParams = props?.props;
    const [searchInput, setSearchInput] = React.useState(passedSearchParams?.query ? passedSearchParams.query : '');
    const [checkIn, setCheckIn] = React.useState(passedSearchParams?.checkIn ? passedSearchParams.checkIn : '');
    const [checkOut, setCheckOut] = React.useState(passedSearchParams?.checkOut ? passedSearchParams?.checkOut : '');
    const [guestAdults, setGuestAdults] = React.useState(passedSearchParams?.adults ? passedSearchParams.adults : 1);
    const [validated, setValidated] = React.useState(false);
    const navigate = useNavigate();
    const dispatch = useDispatch();
    const [searchHotel, { isLoading, error }] = useSearchHotelMutation();

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
        let adults = parseInt(e.target.value);
        setGuestAdults(adults);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const form = e.currentTarget;
        if (form.checkValidity() === false) {
            e.stopPropagation();
            setValidated(true);
            return;
        }
        const data = {
            query: searchInput,
            checkIn: checkIn,
            checkOut: checkOut,
            adults: guestAdults,
            children: 0,
            minPrice : props.props?.minPrice ? props.props.minPrice : 0,
            maxPrice : props.props?.maxPrice ? props.props.maxPrice : 1000000,
        };
        console.log('data for fetching hotels', data);
        try {
            dispatch(setLoading(true))
            const response = await searchHotel(data).unwrap();
            dispatch(setLoading(false))
            console.log('response', response);
            const hotels = response.data.hotel;
            navigate('/search', { state: { hotels: hotels, search: data } });
        } catch (error) {
            dispatch(setLoading(false))
            if(error.status === 400) {
                // bad request
                console.log('error', error);
            } else {
                // server error
                console.log('server error', error);
            }
        }
    };

  return (
    <div className={props.className}>
        <Form className="rounded slide-form" noValidate validated={validated} onSubmit={handleSubmit}>
            <Container fluid="md" className='p-4'>
            <h2>Enjoy Your Holiday</h2>
            <span>Search and Book Hotel.</span>
            <Form.Group className="" controlId="formSearch" >
                <Form.Control type="search" placeholder="Search here" onChange={handleChangeSearch} required/>
            </Form.Group>


            <Form.Group className="" controlId="formCheckIn">
                <Form.Label style={{color: 'white'}}>Check In</Form.Label>
                <Form.Control type="date" placeholder="Check In" onChange={handleChangeCheckIn} required/>
            </Form.Group>

            <Form.Group className="" controlId="formCheckOut">
                    <Form.Label style={{color: 'white'}}>Check Out</Form.Label>
                    <Form.Control type="date" placeholder="Check Out" onChange={handleChangeCheckOut} required/>
            </Form.Group>
            <Form.Group className="" controlId="formAdult">
                    <Form.Label style={{color: 'white'}}>Number of Guest</Form.Label>
                    <Form.Control defaultValue={1} type="number" placeholder="Guest(s)" onChange={handleChangeGuestAdults}/>
            </Form.Group>
            <div className = 'd-grid'>
                <Button variant="primary" type="submit" color='' className='my-2'>Submit</Button>
            </div>
            </Container>
      </Form>
    </div>);
}

export default LocationSearchForm