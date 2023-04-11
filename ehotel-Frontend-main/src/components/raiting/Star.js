
import React from 'react'

const Star = (type, key) => {
  return (
    <div className="d-flex justify-content-between rating">
        <i key={key} className={type.type}></i>
    </div>
  )
}

export default Star