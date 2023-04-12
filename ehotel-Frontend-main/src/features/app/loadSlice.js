import {createSlice} from '@reduxjs/toolkit';

// Create a slice of state for the auth feature
const loadSlice = createSlice({
    name: "load",
    initialState: {isLoading: false},
    reducers: {
        setLoading: (state, action) => {
            state.isLoading = action.payload;
        }
    },
});

export const {setLoading} = loadSlice.actions;
export default loadSlice.reducer;

export const selectCurrentLoading = state => state.load.isLoading;