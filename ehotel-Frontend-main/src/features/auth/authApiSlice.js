import { apiSlice } from "../../app/api/apiSlice";

export const authApiSlice = apiSlice.injectEndpoints({
    endpoints: (builder) => ({
        login: builder.mutation({
            query: (credentials) => ({
                url: "/auth/authenticate",
                method: "POST",
                body: { ...credentials}, // email and password
            }),
        }),
        registerAppUser : builder.mutation({
            query: (appUser) => ({
                url: "/auth/register",
                method: "POST",
                body: { ...appUser}, // email, password and role
            })
        })
    })
});

export const { useLoginMutation, useRegisterAppUserMutation } = authApiSlice;