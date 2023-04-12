import {useState, useEffect, useRef } from 'react'
import { useNavigate, useLocation } from 'react-router-dom'
import { useAddStayMutation } from '../../features/stay/stayApiSlice'
import { Link } from 'react-router-dom'
import {useSelector, useDispatch} from 'react-redux';
import {selectCurrentUser } from '../../features/auth/authSlice';

export const StayForm = () => {
    const [errMsg, setErrMsg] = useState('')
    const errRef = useRef();
    const navigate = useNavigate()
    const user = useSelector(selectCurrentUser)

    const paymentStatusRef = useRef();
    const [paymentStatus, setPaymentStatus] = useState('')

    const paymentTotalRef = useRef();
    const [paymentTotal, setPaymentTotal] = useState('')

    const checkInDateRef = useRef();
    const [checkInDate, setCheckInDate] = useState('')

    const checkOutDateRef = useRef();
    const [checkOutDate, setCheckOutDate] = useState('')

    const creationDateRef = useRef();
    const [creationDate, setCreationDate] = useState('')

    const customerNASRef = useRef();
    const [customerNAS, setCustomerNAS] = useState('')

    const employeeNASRef = useRef();
    const [employeeNAS, setEmployeeNAS] = useState('')

    const roomIdRef = useRef();
    const [roomId, setRoomId] = useState('')

    const requestedExtensionsRef = useRef();
    const [requestedExtensions, setRequestedExtensions] = useState('')

    const stay = {
        "paymentStatus" : paymentStatus,
        "paymentTotal" : paymentTotal,
        "checkInDate" : checkInDate,
        "checkOutDate" : checkOutDate,
        "creationDate" : creationDate,
        "customer" : {
            "NAS" : customerNAS
    
        },
        "employee" : {
            "NAS" : user?.employee?.NAS
        },
        "rooms" : [
            roomId
        ],
        "requestedExtensions" : [
            requestedExtensions
        ]
    }

    const [addStay, { isLoading }] = useAddStayMutation()

    useEffect(() => {
        setErrMsg('') // clear error message
    }, );

    const handleSubmit = async (e) => {
        e.preventDefault()
        try {
            await addStay(stay)
            console.log('New Stay')
            navigate('/')
        } catch (err) {
            if (!err?.status) {
                setErrMsg('No Server Response')
            } else if (err.status === 400) {
                setErrMsg('Missing information')
            } else if (err.status === 403) {
                setErrMsg('Unauthorized')
            } else if(err.status === 404) {
                setErrMsg('User Not Found')
            } 
            else {
                setErrMsg('Add Stay Failed')
            }
            errRef.current.focus() // focus on error message, screen reader will read it
        }
    }

    const handlePaymentStatusInput = (e) => {setPaymentStatus(e.target.value)}
    const handlePaymentTotalInput = (e) => {setPaymentTotal(e.target.value)}
    const handleCheckInDateInput = (e) => {setCheckInDate(e.target.value)}
    const handleCheckOutDateInput = (e) => {setCheckOutDate(e.target.value)}
    const handleCreationDateInput = (e) => {setCreationDate(e.target.value)}
    const handleCustomerNASInput = (e) => {setCustomerNAS(e.target.value)}
    const handleEmployeeNASInput = (e) => {setEmployeeNAS(e.target.value)}
    const handleRoomIdInput = (e) => {setRoomId(e.target.value)}
    const handleRequestedExtensionsInput = (e) => {setRequestedExtensions(e.target.value)}

    const content  = isLoading ? <h1>Loding...</h1> : (
    <section>
            <p ref={errRef} className={errMsg ? "errmsg" : "offscreen"} aria-live="assertive">{errMsg}</p>
            <h1>Add Customer</h1>

            
            <form onSubmit={handleSubmit} className="ui form">
                <div className="field">
                    <div className="two fields">

                        <div className="field">
                            <div>
                                <label htmlFor="paymentStatus">Payment Status:</label>
                                    <select value={paymentStatus} id='paymentStatus' type="text" ref={paymentStatusRef}  onChange={handlePaymentStatusInput}>
                                    <option value="">Choose</option>
                                    <option value="UNPAID">Unpaid</option>
                                    <option value="PAYED">Paid</option>
                                </select>
                                </div>
                            </div>
                            <div className="field">
                            <div>
                                <label htmlFor="paymentTotal">Payment Total:</label>
                                <input
                                    type="text"
                                    id="paymentTotal"
                                    ref={paymentTotalRef}
                                    autoComplete="off"
                                    onChange={handlePaymentTotalInput}
                                    value={paymentTotal}
                                    required
                                />
                            </div>
                            </div>
                            <div className="field">
                            <div>
                                <label htmlFor="checkInDate">Check in Date:</label>
                                <input
                                    type="date"
                                    id="checkInDate"
                                    ref={checkInDateRef}
                                    autoComplete="off"
                                    onChange={handleCheckInDateInput}
                                    value={checkInDate}
                                    required
                                />
                            </div>
                            </div>
                            <div className="field">
                            <div>
                                <label htmlFor="checkOutDate">Check out Date:</label>
                                <input
                                    type="date"
                                    id="checkOutDate"
                                    ref={checkInDateRef}
                                    autoComplete="off"
                                    onChange={handleCheckOutDateInput}
                                    value={checkInDate}
                                    required
                                />
                            </div>
                        </div>
                    </div>
                    <div className="field">
                            <div>
                                <label htmlFor="creationDate">Creation Date:</label>
                                <input
                                    type="text"
                                    id="creationDate"
                                    ref={creationDateRef}
                                    autoComplete="off"
                                    onChange={handleCreationDateInput}
                                    value={creationDate}
                                    required
                                />
                            </div>
                        </div>

     
                        <div className="field">
                            <div>
                                <label htmlFor="employeeNAS">Employee NAS:</label>
                                <input
                                    type="bigint"
                                    id="employeeNAS"
                                    ref={employeeNASRef}
                                    autoComplete="off"
                                    onChange={handleEmployeeNASInput}
                                    value={employeeNAS}
                                    required
                                />
                            </div>
                        </div>
                        <div className="field">
                            <div>
                                <label htmlFor="customerNAS">Customer NAS:</label>
                                <input
                                    type="bigint"
                                    id="customerNAS"
                                    ref={customerNASRef}
                                    autoComplete="off"
                                    onChange={handleCustomerNASInput}
                                    value={customerNAS}
                                    required
                                />
                            </div>
                        </div>
  
                        <div className="field">
                            <div>
                                <label htmlFor="roomId">Room ID:</label>
                                <input
                                    type="bigint"
                                    id="roomId"
                                    ref={roomIdRef}
                                    autoComplete="off"
                                    onChange={handleRoomIdInput}
                                    value={roomId}
                                    required
                                />
                            </div>
                        </div>
                    
                    
                        <div className="field">
                            <div>
                                <label htmlFor="RequestedExtensions">Requested Extensions:</label>
                                <input
                                    type="text"
                                    id="requestedExtensions"
                                    ref={requestedExtensionsRef}
                                    autoComplete="off"
                                    onChange={handleRequestedExtensionsInput}
                                    value={requestedExtensions}
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

export default StayForm
