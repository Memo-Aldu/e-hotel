import { apiSlice } from "../../app/api/apiSlice";

export const customerApiSlice = apiSlice.injectEndpoints({
    endpoints: (builder) => ({
        getCustomerById : builder.mutation({
            query: (id) => ({
                url: `/customer/${id}`,
                method: "GET",
            })
        }),
        addCustomer: builder.mutation({
            query: (customer) => ({
                url: "/customer",
                method: "POST",
                body: { ...customer},
            }),
        }),
        updateCustomer: builder.mutation({
            query: (customer, id) => ({
                url: `/customer/${id}`,
                method: "PATCH",
                body: { ...customer},
            }),
        }),
        deleteCustomer: builder.mutation({
            query: (id) => ({
                url: `/customer/${id}`,
                method: "DELETE",
            })
        }),


    })
});

export const { useGetCustomerByIdMutation, useAddCustomerMutation, useUpdateCustomerMutation, 
    useDeleteCustomerMutation } = customerApiSlice;