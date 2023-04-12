import {useState, useEffect, useRef } from 'react'
import { useNavigate, useLocation } from 'react-router-dom'
import { useAddHotelMutation,useGetHotelByIdMutation } from '../../features/hotel/hotelApiSlice'
import { Link } from 'react-router-dom'

export const HotelForm = () => {    
    const [addHotel, { isLoading }] = useAddHotelMutation();
    const [errMsg, setErrMsg] = useState('')
    const errRef = useRef();

    const navigate = useNavigate()
    useEffect(() => {
        emailRef.current.focus() // focus on email input
    }, []);


    const nameRef = useRef();
    const [name, setName] = useState('')

    const id = useState('')

    const addressRef = useRef();
    const [address, setAddress] = useState('')

    const emailRef = useRef();
    const [email, setEmail] = useState('')

    const phoneNumberRef = useRef();
    const [phoneNumber, setPhoneNumber] = useState('')

    const ratingRef = useRef();
    const [rating, setRating] = useState('')

    const cityRef = useRef();
    const [city, setCity] = useState('')

    const [chainId, setChainId] = useState('')
    
    const [disabled, setDisabled] = useState(true);

 


        const hotel = {
        "id" : id,
        "name": name,
        "address": address,
        "email": email,
        "phoneNumber": phoneNumber,
        "rating": rating,
        "city": city,
        "chainHotel": {
            "id": chainId
    }
}

    
const handleSubmit = async (e) => {
    e.preventDefault()

    try {
        await addHotel(hotel)
        console.log('Room')
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


        

        const handleNameInput = (e) => {setName(e.target.value)}
        const handleAddressInput = (e) => {setAddress(e.target.value)}
        const handleEmailInput = (e) => {setEmail(e.target.value)}
        const handlePhoneNumberInput = (e) => {setPhoneNumber(e.target.value)}
        const handleRatingInput = (e) => {setRating(e.target.value)}
        const handleCityInput = (e) => {setCity(e.target.value)}
        const handleChainIdInput = (e) => {setChainId(e.target.value)}
        const content  = isLoading ? <h1>Loding...</h1> : (
        <section>
        <div>
        <form onSubmit={handleSubmit}>
                <div className="field">
                    <div className="two fields">

                        <div className="field">
                            <div>
                                <label htmlFor="name">Name:</label>
                                <input
                                    type="text"
                                    id="name"
                                    ref={nameRef}
                                    autoComplete="off"
                                    onChange={handleNameInput}
                                    value={name}
                                    required
                                />
                            </div>
                            </div>
                            <div className="field">
                            <div>
                                <label htmlFor="email">Email:</label>
                                <input
                                    type="text"
                                    id="email"
                                    ref={emailRef}
                                    autoComplete="off"
                                    onChange={handleEmailInput}
                                    value={email}
                                    required
                                />
                            </div>
                        </div>
                    </div>
                    <div className="field">
                            <div>
                                <label htmlFor="address">Address:</label>
                                <input
                                    type="text"
                                    id="address"
                                    ref={addressRef}
                                    autoComplete="off"
                                    onChange={handleAddressInput}
                                    value={address}
                                    required
                                />
                            </div>
                        </div>
                    
                    <div className="two fields">
                        <div className="field">
                            <div>
                                <label htmlFor="phoneNumber">Phone Number:</label>
                                <input
                                    type="text"
                                    id="phoneNumber"
                                    ref={phoneNumberRef}
                                    autoComplete="off"
                                    onChange={handlePhoneNumberInput}
                                    value={phoneNumber}
                                    required
                                />
                            </div>
                            </div>
                            <div className="field">
                            <div>
                                <label htmlFor="rating">rating:</label>
                                <select value={rating} type="short"  onChange={handleRatingInput}>
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                </select>
                                </div>
                        </div>
                        <div className="field">
                        <div>
                                <label htmlFor="city">city:</label>
                                <input
                                    type="text"
                                    id="city"
                                    ref={cityRef}
                                    autoComplete="off"
                                    onChange={handleCityInput}
                                    value={city}
                                    required
                                />
                            </div>
                            </div>
                            <div className="field">
                        <div>
                                <label htmlFor="chainId">chainId:</label>
                                <input
                                    type="text"
                                    id="bigint"
                                    autoComplete="off"
                                    onChange={handleChainIdInput}
                                    value={chainId}
                                    required
                                />
                        </div>
                        </div>

                    </div>    
            </div>
            <button>Add</button>
        </form>
        </div>
        </section>  )
    return content;
} 
export default HotelForm;