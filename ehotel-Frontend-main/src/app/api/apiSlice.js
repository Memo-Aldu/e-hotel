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
        return headers;
    }
});

const baseQueryWithReAuth = async (args, api, extraOptions) => {
    let result = await baseQuery(args, api, extraOptions);
    const refreshToken = api.getState().auth.refreshToken;
    if (result?.error?.originalStatus === 401 && refreshToken) {
        console.log('sending refresh request');
        extraOptions.headers.set('Authorization', `Bearer ${refreshToken}`);
        const response = await baseQuery("/auth/token/refresh", api, extraOptions);
        
        console.log('refresh result: ' + JSON.stringify(response));
        if(response?.data?.data) {
            const user = api.getState().auth.user;
            // store the new token
            api.dispatch(setCredentials({user, token: response.data.data.access_token,
                 refresh_Token: response.data.data.refresh_token}));
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