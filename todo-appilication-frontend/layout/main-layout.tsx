"use client";
import HeaderComponent from "@/components/header/Header";
import SnackbarProvider from "@/components/snackbar";
import Providers from "@/provider/providers";
import { store } from "@/redux/store";
import { Provider } from "react-redux";

export default function MainLayout({
    children,
}: Readonly<{
    children: React.ReactNode;
}>) {
    return (
        <>
        <Providers>
            <SnackbarProvider>
            <Provider store={store} >
                <HeaderComponent/>
                <div className="min-h-screen">
                    {children}
                </div>
            </Provider>
            </SnackbarProvider>
        </Providers>
        </>
    );
}