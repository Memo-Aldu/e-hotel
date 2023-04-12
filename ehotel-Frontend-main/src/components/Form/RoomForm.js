
import {useState, useEffect, useRef } from 'react'
import { useNavigate, useLocation } from 'react-router-dom'
import { useAddRoomMutation } from '../../features/room/roomApiSlice'
import { Link } from 'react-router-dom'
import {selectCurrentUser } from '../../features/auth/authSlice';
import {useSelector, useDispatch} from 'react-redux';
import { useGetHotelByIdMutation } from '../../features/hotel/hotelApiSlice';

export const RoomForm = () => {
    const [errMsg, setErrMsg] = useState('')
    const errRef = useRef();
    const user = useSelector(selectCurrentUser)

    const getHotel = useGetHotelByIdMutation()
    const dispatch = useDispatch()
    const navigate = useNavigate();

    const [typeId,setTypeId] = useState('');
    const [roomNumber,setRoomNumber] = useState('');

    const room = {
        "hotel" :{
            "id": user?.employee?.role?.hotel?.id
        },
        "type" : {
            "id": typeId
        },
        "roomNumber": roomNumber
    }
    const [addRoom, { isLoading }] = useAddRoomMutation()

    useEffect(() => {
        setErrMsg('') // clear error message
    }, );

    const handleSubmit = async (e) => {
        e.preventDefault()
        try {
            await addRoom(room)
            console.log(user?.employee?.role?.hotel?.id)

            navigate('/')
        } catch (err) {
            if (!err?.status) {
                setErrMsg('No Server Response')
            } else if (err.status === 400) {
                setErrMsg('Missing email or Password')
            } else if (err.status === 403) {
                setErrMsg('Unauthorized')
            } else if(err.status === 404) {
                setErrMsg('User Not Found')
            } 
            else {
                setErrMsg('Add Failed')
            }
            errRef.current.focus() // focus on error message, screen reader will read it
        }
    }

    const handleRoomNumberInput = (e) => {setRoomNumber(e.target.value)}
    const handleTypeIdInput = (e) => {setTypeId(e.target.value)}
  
    const content  = isLoading ? <h1>Loading...</h1> : (
    <section>
            <p ref={errRef} className={errMsg ? "errmsg" : "offscreen"} aria-live="assertive">{errMsg}</p>
            <h1>Add Room</h1>

            
            <form onSubmit={handleSubmit} className="ui form">
                    <div className="field">
                        <div className="two fields">

    
     {/*                       <div className="field">
                                <div>
                                    <label htmlFor="hotelId">HotelId:</label>
                                    <input
                                        type="bigint"
                                        id="hotelId"
                                        autoComplete="off"
                                        onChange={handleHotelIdInput}
                                        value={hotelId}
                                        required
                                    />
                                </div>
                                </div>
    */}
                                <div className="field">
                                <div>
                                <label htmlFor="typeId">Type Id:</label>
                                    <input
                                        type="bigint"
                                        id="typeId"
                                        autoComplete="off"
                                        onChange={handleTypeIdInput}
                                        value={typeId}
                                        required
                                    />
                                </div>
                            </div>
                        </div>
                        <div className="field">
                                <div>
                                <label htmlFor="roomNumber">Room Number:</label>
                                    <input
                                        type="bigint"
                                        id="roomNumber"
                                        autoComplete="off"
                                        onChange={handleRoomNumberInput}
                                        value={roomNumber}
                                        required
                                    />
                                </div>
                            </div>
                        
                </div>
                <button>Add</button>
            </form>
            <p>
                Return
                <span className="line">
                    <Link to="/">Back</Link>
                </span>
            </p>
        </section>)
  return content;
    }

export default RoomForm

