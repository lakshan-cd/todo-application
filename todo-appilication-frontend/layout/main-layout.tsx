"use client";
import HeaderComponent from "@/components/header/Header";
import SnackbarProvider from "@/components/snackbar";
import Providers from "@/provider/providers";

export default function MainLayout({
    children,
}: Readonly<{
    children: React.ReactNode;
}>) {
    return (
        <>
        <Providers>
            <SnackbarProvider>
                <HeaderComponent/>
                <div className="min-h-screen">
                    {children}
                </div>
            </SnackbarProvider>
        </Providers>
        </>
    );
}