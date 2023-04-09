import { apiSlice } from "../../app/api/apiSlice";

export const hotelApiSlice = apiSlice.injectEndpoints({
    endpoints: (builder) => ({
        getHotelById : builder.mutation({
            query: (id) => ({
                url: `/hotel/${id}`,
                method: "GET",
            })
        }),
        searchHotel: builder.mutation({
            query: (searchParams) => ({
                url: "/hotel",
                method: "GET",
                body: { ...searchParams},
            }),
        }),
        addHotel: builder.mutation({
            query: (hotel) => ({
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

export const { useGetHotelByIdMutation, useSearchHotelMutation, 
    useAddHotelMutation, useUpdateHotelMutation, useDeleteHotelMutation } = hotelApiSlice;