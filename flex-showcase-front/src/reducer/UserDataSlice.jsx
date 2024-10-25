import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import api from "../common/api";

export const getUser = createAsyncThunk('get/user', async () => {
    const res = await api.get("/my-page");
    console.log('(경고!) dispatch 했음 최대한 이거 안뜨게 하자');
    if(res){
        return res.data;
    } else {
        return null;
    }
});

const userSlice = createSlice({
    name: 'user',
    initialState: {
        data: null,
        status: 'idle',     //idle, loading, success, fail 으로 구분할거
        error: null,
    },
    reducers: {},
    extraReducers: (builder) => {
        builder
        .addCase(getUser.pending, (state) => {
            state.status = 'loading';
        })
        .addCase(getUser.fulfilled, (state, action) => {
            state.status = 'success';
            state.data = action.payload;
        })
        .addCase(getUser.rejected, (state, action) => {
            state.status = 'fail';
            state.error = action.error.message;
        });
    },
});

export default userSlice.reducer;