import {createSlice} from '@reduxjs/toolkit';

// Create a slice of state for the auth feature
const authSlice = createSlice({
    name: "auth",
    initialState: {user: null, token: null},
    reducers: {
        setCredentials: (state, action) => {
            console.log("setting credentials", JSON.stringify(action.payload));
            const {user, accessToken} = action.payload;
            state.user = user;
            state.token = accessToken;
        },
        logOut : (state, action) => {
            console.log("logging out");
            state.user = null;
            state.token = null;
        }
    },
});

export const {setCredentials, logOut} = authSlice.actions;
export default authSlice.reducer;

export const selectCurrentUser = state => state.auth.user;
export const selectCurrentToken = state => state.auth.token;