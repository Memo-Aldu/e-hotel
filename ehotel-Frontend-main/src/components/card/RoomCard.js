import React from 'react'

const RoomCard = (room) => {
    const roomType = room?.type;
    const hotel = room?.hotel;
    const roomView = roomType?.view;
  return (
    <>
    <div className="text-black row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow h-md-250 position-relative">
        <div className="col-4 d-flex">
            <img width='100%' src={require('../../assets/images/mark-champs-Id2IIl1jOB0-unsplash.jpg')} alt="First slide" />
        </div>
        <div className="col-8 p-4">
            <div className='row'>
                <div className="col-8 d-flex justify-content-start">
                    <h2 className="text-success">{roomType?.roomName ? roomType?.roomName : 'Room'}</h2>
                    <h2 className="ps-2 text-success">
                        {roomType?.pricePerNight ? roomType?.pricePerNight + '$ per night' : ''}</h2>
                </div>
            </div>
            <div className='row d-flex align-text-bottom  text-black'>
                <div className="mb-1">
                    {roomType?.roomDescription ? "Description: " + roomType?.roomDescription : '' }
                    {roomType?.capacity ? " with capacity: " + roomType?.capacity : ''}
                </div>
                <div className="mb-1">
                    {(room?.hotel?.name? room?.hotel?.name + ":" : '') + ' ' +  ((hotel?.city) ? 'City: ' + hotel?.city : '')}
                </div>
                <p className="mb-auto text-black ">
                    {roomView?.name ? "View: " + roomView?.name : ''}
                </p>
                <p className="mb-auto text-success fw-bold ">Free Cancellation  • No prepayment needed</p>
                <div className="mb-1 text-muted">You can cancel later, so lock in this great price today!</div>
            </div>
            <div className='d-flex justify-content-end'>
                <button type='button' className="btn btn btn-outline-primary">Create Reservation</button>
            </div>

        </div>
    </div>
    </>
  )
}

export default RoomCard