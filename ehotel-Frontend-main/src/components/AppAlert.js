import React, { useEffect, useState } from 'react'
import { Alert } from 'react-bootstrap';

const AppAlert = (props) => {

    const [visibleAlert, setVisibleAlert] = useState(false);

    useEffect(() => {
        if (props.props.visible) {
            handleVisible()
        }
    }, [props])

    const handleVisible = () => {
        setVisibleAlert(true)
            setTimeout(() => {
                setVisibleAlert(false)
            }, 3000);
    } 

    return (
        <>
            <Alert show={props.props ? visibleAlert : false} className={props.props.alertType ? props.props.alertType : 'alert alert-primary'} role="alert">
                {props.props && props.props.msg ? props.props.msg : ''}
            </Alert>
        </>
    )
}

export default AppAlert