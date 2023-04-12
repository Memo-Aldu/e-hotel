
import React from 'react'
import Stars from './../raiting/Stars'
import { useNavigate } from 'react-router-dom'
import { useDispatch, useSelector } from 'react-redux';
import { useGetAllRoomsByHotelIdMutation } from '../../features/room/roomApiSlice';
import { setLoading } from '../../features/app/loadSlice';

const HotelSearchCard = (hotel) => {
    const dispatch = useDispatch()
    const [getAllRoomsByHotelId] = useGetAllRoomsByHotelIdMutation()
    const navigate = useNavigate()
    const handleClick = async (e) => {
        try {
            e.preventDefault()
            dispatch(setLoading(true))
            const response = await getAllRoomsByHotelId(hotel.id).unwrap()
            dispatch(setLoading(false))
            navigate('/room', { state: { rooms: response?.data?.rooms } })
        } catch (error) {
            console.log(error)
        }
    }
    return (
        <>
        <div className="text-black row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow h-md-250 position-relative">
            <div className="col-4 d-flex">
                <img width='100%' src={require('../../assets/images/hotel-carousel-1.jpg')} alt="First slide" />
            </div>
            <div className="col-8 p-4">
                <div className='row'>
                    <div className="col-8 d-flex justify-content-start">
                        <h2 className="text-success">{hotel.name ? hotel.name : 'Hotel'}</h2>
                    </div>
                    <div className="col-4">
                        <div className="pt-2 d-flex justify-content-end" style={{color: 'black'}}>
                            <Stars value={hotel.rating ? hotel.rating : 0}/>
                        </div>
                    </div>
                </div>
                <div className='row d-flex align-text-bottom'>
                    <div className="mb-1">
                        {((hotel.chainHotel?.name) ? 'Chain: ' + hotel.chainHotel?.name : '')
                        + " : " +  ((hotel.city) ? 'City: ' + hotel.city : '') }
                        </div>
                    <p className="mb-auto text-success fw-bold ">Breakfast Included</p>
                    <p className="mb-auto text-success fw-bold ">Free Cancellation  • No prepayment needed</p>
                    <div className="mb-1 text-muted">You can cancel later, so lock in this great price today!</div>
                </div>
                <div className='d-flex justify-content-end'>
                    <button type='button' onClick={handleClick} className="btn btn btn-outline-primary">See Availability</button>
                </div>
            </div>
        </div>
        </>
    )
}

export default HotelSearchCard