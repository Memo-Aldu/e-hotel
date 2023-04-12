import { apiSlice } from "../../app/api/apiSlice";

export const incidentApiSlice = apiSlice.injectEndpoints({
    endpoints: (builder) => ({
        getIncidentByRoomId : builder.mutation({
            query: (roomId) => ({
                url: `/incidents/room/${roomId}`,
                method: "GET",
            })
        }),
        getIncidentById : builder.mutation({
            query: (id) => ({
                url: `/incidents/${id}`,
                method: "GET",
            })
        }),
        addIncident: builder.mutation({
            query: (incident) => ({
                url: "/incidents",
                method: "POST",
                body: { ...incident},
            }),
        }),
        updateIncident: builder.mutation({
            query: (incident, id) => ({
                url: `/incidents/${id}`,
                method: "PATCH",
                body: { ...incident},
            }),
        }),
        deleteIncident: builder.mutation({
            query: (id) => ({
                url: `/incidents/${id}`,
                method: "DELETE",
            })
        }),


    })
});

export const { useGetIncidentByIdMutation,  useGetIncidentByRoomIdMutation, useAddIncidentMutation, useUpdateIncidentMutation, 
    useDeleteIncidentMutation } = incidentApiSlice;