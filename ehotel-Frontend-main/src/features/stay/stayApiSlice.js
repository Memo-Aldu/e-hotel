import { apiSlice } from "../../app/api/apiSlice";

export const stayApiSlice = apiSlice.injectEndpoints({
    endpoints: (builder) => ({
        getStayById : builder.mutation({
            query: (id) => ({
                url: `/stay/${id}`,
                method: "GET",
            })
        }),
        getStayByCustomerNAS : builder.mutation({
            query: (NAS) => ({
                url: `/stay/customer/${NAS}`,
                method: "GET",
            })
        }),
        getStayByHotelId : builder.mutation({
            query: (hotelId) => ({
                url: `/stay/hotel/${hotelId}`,
                method: "GET",
            })
        }),
        addStay: builder.mutation({
            query: (stay) => ({
                url: "/stay",
                method: "POST",
                body: { ...stay},
            }),
        }),
        updateStay: builder.mutation({
            query: (stay, id) => ({
                url: `/stay/${id}`,
                method: "PATCH",
                body: { ...stay},
            }),
        }),
        deleteStay: builder.mutation({
            query: (id) => ({
                url: `/stay/${id}`,
                method: "DELETE",
            })
        }),


    })
});

export const { useGetStayByIdMutation,useGetStayByCustomerNASMutation,useGetStayByHotelIdMutation,  
    useAddStayMutation, useUpdateStayMutation, useDeleteStayMutation } = stayApiSlice;