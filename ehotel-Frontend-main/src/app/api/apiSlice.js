import {createApi, fetchBaseQuery} from '@reduxjs/toolkit/query/react'
import { setCredentials, logOut } from '../../features/auth/authSlice'

const baseQuery = fetchBaseQuery({
    // TOD: change for production
    baseUrl: 'http://localhost:8080/api/v1',
    credentials: 'include',
    prepareHeaders: (headers, { getState }) => {
        const token = getState().auth.token;
        if (token) {
            headers.set('Authorization', `Bearer ${token}`);
        }

        headers.set('Content-Type', 'application/json');
        headers.set('Access-Control-Allow-Origin', 'localhost:3000');
        headers.set('Access-Control-Allow-Methods', 'GET, PUT,POST ,DELETE, PATCH, OPTIONS');
        headers.set("Access-Control-Allow-Credentials", "true");
        return headers;
    }
});

const baseQueryWithReAuth = async (args, api, extraOptions) => {
    let result = await baseQuery(args, api, extraOptions);
    if (result?.error?.originalStatus === 401) {
        console.log('sending refresh request');
        //send refresh token
        const refreshResult = await baseQuery("/auth/token/refresh", api, extraOptions);
        console.log('refresh result: ' + JSON.stringify(refreshResult));
        if(refreshResult?.data?.data) {
            const user = api.getState().auth.user;
            // store the new token
            api.dispatch(setCredentials({user, token: refreshResult.data.data.access_token}));
            // retry the original request
            result = await baseQuery(args, api, extraOptions);
        } else {
            // log out
            api.dispatch(logOut());
        }
    }
    return result;
};

export const apiSlice = createApi({
    baseQuery: baseQueryWithReAuth,
    endpoints: (builder) => ({}), // empty object will specify endpoints elsewhere
});