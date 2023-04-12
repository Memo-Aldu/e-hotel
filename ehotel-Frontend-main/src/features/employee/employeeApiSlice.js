import { apiSlice } from "../../app/api/apiSlice";

export const employeeApiSlice = apiSlice.injectEndpoints({
    endpoints: (builder) => ({
        getEmployeeById : builder.mutation({
            query: (id) => ({
                url: `/employee/${id}`,
                method: "GET",
            })
        }),
        addEmployee: builder.mutation({
            query: (employee) => ({
                url: "/employee",
                method: "POST",
                body: { ...employee},
            }),
        }),
        updateEmployee: builder.mutation({
            query: (employee, id) => ({
                url: `/employee/${id}`,
                method: "PATCH",
                body: { ...employee},
            }),
        }),
        deleteEmployee: builder.mutation({
            query: (id) => ({
                url: `/emloyee/${id}`,
                method: "DELETE",
            })
        }),


    })
});

export const { useGetEmployeeByIdMutation, useAddEmployeeMutation, useUpdateEmployeeMutation, 
    useDeleteEmployeeMutation } = employeeApiSlice;