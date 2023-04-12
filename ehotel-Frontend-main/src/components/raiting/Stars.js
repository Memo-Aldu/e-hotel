import React from 'react'
import styles from './Stars.css'
import Star from './Star'


const NUM_STARS = 5;
const Stars = (props) => {
    const stars = Array(NUM_STARS).fill(<Star type={'fa-regular fa-star'}/>)
        .fill(<Star type= {'fa-solid fa-star'}/>, 0, Math.floor(props.value));
    if (props.value % 1 !== 0) // if value is a decimal, add a half star
      stars[Math.floor(props.value)] = <Star type={'fa-regular fa-star-half-stroke'}/>;
  
    return stars;
}


export default Stars;