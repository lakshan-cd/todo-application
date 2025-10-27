"use client";

import { ThemeProvider } from "next-themes";

const Providers = ({ children }: { children: React.ReactNode }) => {
    return (
        <ThemeProvider
            attribute="class"
            defaultTheme="light"
            enableSystem={false}
            disableTransitionOnChange={false}
            storageKey="taskflow-theme"
        >
            {children}
        </ThemeProvider>
    );
};

export default Providers;