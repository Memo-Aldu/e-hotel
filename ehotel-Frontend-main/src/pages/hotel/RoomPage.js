import RoomCard from '../../components/card/RoomCard';
import React, { useEffect } from 'react'
import { AppNavbar } from '../../components/Navbar/Navbar'
import { Row ,Col, Form, Container } from 'react-bootstrap'
import LocationSearchForm from '../../components/Form/LocationSearchForm'
import HotelSearchCard from '../../components/card/HotelSearchCard'
import { useNavigate, useLocation } from 'react-router-dom'
import { MDBCheckbox, MDBRadio  } from 'mdb-react-ui-kit';
import AppAlert from '../../components/AppAlert'
import AppSpinner from '../../components/spinner/Spinner'
import { useSelector, useDispatch } from 'react-redux'
import { selectCurrentLoading } from '../../features/app/loadSlice'
import {useGetAllRoomsByHotelIdMutation} from '../../features/room/roomApiSlice'
import { setLoading } from '../../features/app/loadSlice';




const RoomPage = () => {

  const {state} = useLocation();
  const [rooms, setRooms] = React.useState(state?.rooms); //
  const navigate = useNavigate();
  const [msg, setMsg] = React.useState('');
  const [alertType, setAlertType] = React.useState('');
  const dispatch = useDispatch()
  const loading = useSelector(selectCurrentLoading)

  return (
    <>
    <div className={loading ? 'loader' : 'd-none'}>
          <AppSpinner/>  
    </div>
    <AppNavbar/>
    <Container className="fluid">
      <main class="container text-white rounded bg-white">
      {/*         Header*/}        
      <div class="p-4 p-md-2 mb-4 text-white rounded bg-dark">
          <Row>
            <img
              className="d-block w-50"
              src={require('../../assets/images/hotel-carousel-1.jpg')}
              alt="First slide"
            />
            <div class="col-md-6 px-0">
              <h1 class="display-4 fst-italic">Reserve Some Rooms For Your Stay...</h1>
              <p class="lead my-3">Multiple lines of text that form the lede, informing new readers quickly and efficiently about what’s most interesting in this post’s contents.</p>
              <p class="lead mb-0"><a href="#" class="text-white fw-bold">Continue reading...</a></p>
          </div>
          </Row>
        </div>
        <div class="row mb-2">
          <div class="col-md-4 col-lg-3">
            <div className='row'>
              <LocationSearchForm className='color-overlay align-items-center' 
              props={{... state?.search}}/>
            </div>
          </div>
          <div className="col-md-8 col-lg-9">
            <AppAlert props={{visible: loading, msg: msg, alertType: alertType}}/>
            {
              state && rooms && rooms.map((room, index) => {
                if(room?.status === 'UNOCCUPIED')
                return <RoomCard key={`room-${room.id}`} {...room} />
              })
            }
          </div>
        </div>
      </main>

    </Container>
    </>
  )
}

export default RoomPage