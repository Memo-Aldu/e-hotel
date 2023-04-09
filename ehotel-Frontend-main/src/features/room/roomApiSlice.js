import { apiSlice } from "../../app/api/apiSlice";

export const roomApiSlice = apiSlice.injectEndpoints({
    endpoints: (builder) => ({
        getRoomById : builder.mutation({
            query: (id) => ({
                url: `/room/${id}`,
                method: "GET",
            })
        }),
        searchRoom: builder.mutation({
            query: () => ({
                url: "/room/search",
                method: "GET",
            }),
        }),
        addRoom: builder.mutation({
            query: (room) => ({
                url: "/room",
                method: "POST",
                body: { ...room},
            }),
        }),
        updateRoom: builder.mutation({
            query: (room, id) => ({
                url: `/room/${id}`,
                method: "PATCH",
                body: { ...room},
            }),
        }),
        deleteRoom: builder.mutation({
            query: (id) => ({
                url: `/room/${id}`,
                method: "DELETE",
            })
        }),


    })
});

export const { useGetRoomByIdMutation,useSearchRoomMutation,  
    useAddRoomMutation, useUpdateRoomMutation, useDeleteRoomMutation } = roomApiSlice;