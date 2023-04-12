import { apiSlice } from "../../app/api/apiSlice";

export const hotelChainApiSlice = apiSlice.injectEndpoints({
    endpoints: (builder) => ({
        getHotelChainById : builder.mutation({
            query: (id) => ({
                url: `/hotel-chain/${id}`,
                method: "GET",
            })
        }),
        getAllHotelChain: builder.mutation({
            query: () => ({
                url: "/hotel-chain",
                method: "GET",
            }),
        }),
        addHotelChain: builder.mutation({
            query: (chain) => ({
                url: "/hotel-chain",
                method: "POST",
                body: { ...chain},
            }),
        }),
        updateHotelChain: builder.mutation({
            query: (chain, id) => ({
                url: `/hotel-chain/${id}`,
                method: "PATCH",
                body: { ...chain},
            }),
        }),
        deleteHotelChain: builder.mutation({
            query: (id) => ({
                url: `/hotel-chain/${id}`,
                method: "DELETE",
            })
        }),


    })
});

export const { useGetHotelChainByIdMutation,useGetAllHotelChainMutation,  
    useAddHotelChainMutation, useUpdateHotelChainMutation, useDeleteHotelChainMutation } = hotelChainApiSlice;