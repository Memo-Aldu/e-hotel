import {createSlice} from '@reduxjs/toolkit';

// Create a slice of state for the auth feature
const authSlice = createSlice({
    name: "auth",
    initialState: {user: null, token: null, refreshToken: null},
    reducers: {
        setCredentials: (state, action) => {
            console.log("setting credentials", JSON.stringify(action.payload));
            const {user, accessToken, refreshToken} = action.payload;
            if(user) {
                state.user = user;
            }
            if(accessToken) {
                state.token = accessToken;
            }
            if(refreshToken) {
                state.refreshToken = refreshToken;
            }
        },
        logOut : (state, action) => {
            console.log("logging out");
            state.user = null;
            state.token = null;
            state.refreshToken = null;
        }
    },
});

export const {setCredentials, logOut} = authSlice.actions;
export default authSlice.reducer;

export const selectCurrentUser = state => state.auth.user;
export const selectCurrentToken = state => state.auth.token;
export const selectCurrentRefreshToken = state => state.auth.refreshToken;