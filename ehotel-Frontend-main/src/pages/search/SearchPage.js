import React, { useEffect } from 'react'
import { AppNavbar } from '../../components/Navbar/Navbar'
import { Row ,Col, Form, Container } from 'react-bootstrap'
import LocationSearchForm from '../../components/Form/LocationSearchForm'
import HotelSearchCard from '../../components/card/HotelSearchCard'
import { useNavigate, useLocation } from 'react-router-dom'
import { MDBCheckbox, MDBRadio  } from 'mdb-react-ui-kit';
import AppAlert from '../../components/AppAlert'
import AppSpinner from '../../components/spinner/Spinner'
import { useSelector } from 'react-redux'
import { selectCurrentLoading } from '../../features/app/loadSlice'
import RangeSlider from 'react-bootstrap-range-slider';



const SearchPage = () => {
  const { state } = useLocation();
  const [oneStarChecked, setOneStarChecked] = React.useState(false);
  const [twoStarChecked, setTwoStarChecked] = React.useState(false);
  const [threeStarChecked, setThreeStarChecked] = React.useState(false);
  const [fourStarChecked, setFourStarChecked] = React.useState(false);
  const [fiveStarChecked, setFiveStarChecked] = React.useState(false);
  const [priceRange, setPriceRange] = React.useState([0, 100]);
  const [hotels, setHotels] = React.useState([]); // [
  const [msg, setMsg] = React.useState('');
  const [alertType, setAlertType] = React.useState('');

  const loading = useSelector(selectCurrentLoading)


  useEffect(() => {
    if (state?.hotels?.length > 0 && hotels.length > 0) {
      setHotels(state.hotels)
      setAlertType('alert alert-success')
      setMsg('Search results found')
    } else if (state?.hotels?.length === 0 || hotels.length === 0) {
      setHotels([])
      setAlertType('alert alert-warning')
      setMsg('No search results found')
    } else {
      setHotels([])
      setAlertType('alert alert-danger')
      setMsg('Error in search')
    }
  }, [state])

  useEffect(() => {
    const filteredHotels = [];
    if(state?.hotels) {
      if (oneStarChecked) {
        const oneStar = state.hotels.filter(hotel => hotel.rating === 1 || hotel.rating === 1.5)
        filteredHotels.push(...oneStar)
      }
      if (twoStarChecked) {
        let twoStar = state.hotels.filter(hotel => hotel.rating === 2 || hotel.rating === 2.5)
        filteredHotels.push(...twoStar)
      }
      if (threeStarChecked) {
        const threeStar = state.hotels.filter(hotel => hotel.rating === 3 || hotel.rating === 1.5)
        filteredHotels.push(...threeStar)
      }
      if (fourStarChecked) {
        const fourStar = state.hotels.filter(hotel => hotel.rating === 4 || hotel.rating === 4.5)
        filteredHotels.push(...fourStar)
      }
      if (fiveStarChecked) {
        const fiveStar = state.hotels.filter(hotel => hotel.rating === 5)
        filteredHotels.push(...fiveStar)
      }
      if((fiveStarChecked || fourStarChecked || threeStarChecked || twoStarChecked || oneStarChecked) && filteredHotels.length == 0) {
        setHotels([])
      } else if(filteredHotels.length > 0) {
        setHotels(filteredHotels)
      } else {
        setHotels(state.hotels)
      }
    } else {
      setHotels([])
    }
   
  }, [oneStarChecked, twoStarChecked, threeStarChecked, fourStarChecked, fiveStarChecked, state?.hotels]);

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
              <h1 class="display-4 fst-italic">Search for Hotels</h1>
              <p class="lead my-3">Multiple lines of text that form the lede, informing new readers quickly and efficiently about what’s most interesting in this post’s contents.</p>
              <p class="lead mb-0"><a href="#" class="text-white fw-bold">Continue reading...</a></p>
          </div>
          </Row>
        </div>
        <div class="row mb-2">
          <div class="col-md-4 col-lg-3">
            <div className='row'>
              <LocationSearchForm className='color-overlay align-items-center' 
              props={{minPrice: priceRange[0], maxPrice: priceRange[1], ... state?.search}}/>
            </div>
            <div className="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
              <div className="col p-4 d-flex flex-column position-static">
                <h3 className="d-inline-block mb-2 text-dark">Hotel Rating</h3>
                <p className="mb-0 text-black">Filter by rating</p>
                <div class="text-secondary">
                  <MDBCheckbox name='flexCheck' value='' id='flexCheckStar1' label='1 star' onClick={() => setOneStarChecked(!oneStarChecked)}/>
                  <MDBCheckbox name='flexCheck' value='' id='flexCheckStar2' label='2 stars'onClick={() => setTwoStarChecked(!twoStarChecked)}/>
                  <MDBCheckbox name='flexCheck' value='' id='flexCheckStar3' label='3 stars'onClick={() => setThreeStarChecked(!threeStarChecked)}/>
                  <MDBCheckbox name='flexCheck' value='' id='flexCheckStar4' label='4 stars'onClick={() => setFourStarChecked(!fourStarChecked)}/>
                  <MDBCheckbox name='flexCheck' value='' id='flexCheckStar5' label='5 stars'onClick={() => setFiveStarChecked(!fiveStarChecked)}/>
              </div>
              </div>
            </div>
            <div className="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
              <div className="col p-4 d-flex flex-column position-static">
                <h3 className="d-inline-block mb-2 text-dark">Your Budget</h3>
                <p className="mb-0 text-black">Filter by price per night</p>
                <div>
                  <Form className=''>
                    <Form.Group as={Row}>
                      <Col column xsm="8" className=' pt-2 text-black'>
                        <RangeSlider tooltip='off'
                          value={priceRange[1] /2}
                          onChange={e => setPriceRange([0, e.target.value  * 2])}
                        />
                      </Col>
                      <Form.Label column className='' xsm='4'>
                        {priceRange[1]}$
                      </Form.Label>
                    </Form.Group>
                  </Form>
                  <div class="text-secondary">
                    <MDBRadio  name='flexRadioDefault' value='' id='flexRadioDefault1' label='0 - 50$' onClick={() => setPriceRange([0, 50])}/>
                    <MDBRadio  name='flexRadioDefault' value='' id='flexRadioDefault2' label='50 - 100$'onClick={() => setPriceRange([50, 100])}/>
                    <MDBRadio  name='flexRadioDefault' value='' id='flexRadioDefault3' label='100 - 150$'onClick={() => setPriceRange([100, 150])}/>
                    <MDBRadio  name='flexRadioDefault' value='' id='flexRadioDefault4' label='200$ +' defaultChecked onClick={() => setPriceRange([150, 1000])}/>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div className="col-md-8 col-lg-9">
            {/* Filter the hotel by rating */}
            <AppAlert props={{visible: loading, msg: msg, alertType: alertType}}/>
            {
              state && hotels && hotels.map((hotel, index) => {
                return <HotelSearchCard key={`hotel-${hotel.id}`} {...hotel} />
              })
            }
          </div>
        </div>
      </main>

    </Container>
    </>
  )
}

export default SearchPage