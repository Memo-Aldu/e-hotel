import { apiSlice } from "../../app/api/apiSlice";

export const reservationApiSlice = apiSlice.injectEndpoints({
    endpoints: (builder) => ({
        getReservationByCustomerNAS : builder.mutation({
            query: (customerNAS) => ({
                url: `/reservation/customer/${customerNAS}}`,
                method: "GET",
            })
        }),
        getReservationByHotelId : builder.mutation({
            query: (hotelId) => ({
                url: `/reservation/hotel/${hotelId}}`,
                method: "GET",
            })
        }),
        getReservationById : builder.mutation({
            query: (id) => ({
                url: `/reservation/${id}`,
                method: "GET",
            })
        }),
        addReservation: builder.mutation({
            query: (reservation) => ({
                url: "/reservation",
                method: "POST",
                body: { ...reservation},
            }),
        }),
        updateReservation: builder.mutation({
            query: (reservation, id) => ({
                url: `/reservation/${id}`,
                method: "PATCH",
                body: { ...reservation},
            }),
        }),
        deleteReservation: builder.mutation({
            query: (id) => ({
                url: `/reservation/${id}`,
                method: "DELETE",
            })
        }),


    })
});

export const { useGetReservationByIdMutation,  useGetReservationByCustomerNASMutation, useGetReservationByHotelIdMutation, useAddReservationMutation, useUpdateReservationMutation, 
    useDeleteReservationMutation } = reservationApiSlice;