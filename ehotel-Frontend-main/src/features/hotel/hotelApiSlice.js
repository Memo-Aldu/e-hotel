import { apiSlice } from "../../app/api/apiSlice";

export const hotelApiSlice = apiSlice.injectEndpoints({
    endpoints: (builder) => ({
        getHotelById : builder.mutation({
            query: (id) => ({
                url: `/hotel/${id}`,
                method: "GET",
            })
        }),
        getAllHotel: builder.mutation({
            query: () => ({
                url: "/hotel",
                method: "GET",
            }),
        }),
        searchHotel: builder.mutation({
            query: (searchParams) => ({
                url: "/hotel",
                method: "GET",
                body: { ...searchParams},
            }),
        }),
        addHotel: builder.mutation({
            query: (hotel) => (console.log("hotel: " + JSON.stringify(hotel)),
            {
                
                url: "/hotel",
                method: "POST",
                body: { ...hotel},
            }),
        }),
        updateHotel: builder.mutation({
            query: (hotel, id) => ({
                url: `/hotel/${id}`,
                method: "PATCH",
                body: { ...hotel},
            }),
        }),
        deleteHotel: builder.mutation({
            query: (id) => ({
                url: `/hotel/${id}`,
                method: "DELETE",
            })
        }),


    })
});

export const { useGetHotelByIdMutation,useGetAllHotelMutation, useSearchHotelMutation, 
    useAddHotelMutation, useUpdateHotelMutation, useDeleteHotelMutation } = hotelApiSlice;