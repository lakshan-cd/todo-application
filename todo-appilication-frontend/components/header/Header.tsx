'use client';

import { useState } from 'react';
import ThemeSwitcher from './theme-switcher';

const HeaderComponent = () => {
    const [isProfileOpen, setIsProfileOpen] = useState(false);

    return (
        <header className="glass-effect sticky top-0 z-50 border-b border-gray-200 dark:border-slate-700 w-full">
            <div className="flex items-center justify-between px-6 py-4 w-full">
                {/* App Name */}
                <div className="flex items-center space-x-4 flex-shrink-0">
                    <div className="w-10 h-10 bg-gradient-to-r from-[#25CCF7] to-[#1B9CFC] rounded-xl flex items-center justify-center">
                        <span className="text-white font-bold text-lg">T</span>
                    </div>
                    <div>
                        <h1 className="text-2xl font-bold gradient-text">TaskFlow</h1>
                        <p className="text-sm text-gray-600 dark:text-gray-400">Organize your life</p>
                    </div>
                </div>

                {/* Right side controls */}
                <div className="flex items-center space-x-6 flex-shrink-0">
                    {/* Theme Toggle */}
                    <ThemeSwitcher />

                    {/* User Profile */}
                    <div className="relative">
                        <button
                            onClick={() => setIsProfileOpen(!isProfileOpen)}
                            className="flex items-center space-x-3 p-2 rounded-lg hover:bg-gray-100 dark:hover:bg-slate-700 transition-all duration-300"
                        >
                            <div className="w-8 h-8 bg-gradient-to-r from-[#3B3B98] to-[#182C61] rounded-full flex items-center justify-center">
                                <span className="text-white text-sm font-medium">JD</span>
                            </div>
                            <span className="text-sm font-medium text-gray-700 dark:text-gray-300 hidden sm:block">John Doe</span>
                            <svg className="w-4 h-4 text-gray-500" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M19 9l-7 7-7-7" />
                            </svg>
                        </button>

                        {/* Profile Dropdown */}
                        {isProfileOpen && (
                            <div className="absolute right-0 mt-2 w-48 bg-white dark:bg-slate-800 rounded-lg shadow-lg border border-gray-200 dark:border-slate-700 py-2 animate-scale-in">
                                <a href="#" className="block px-4 py-2 text-sm text-gray-700 dark:text-gray-300 hover:bg-gray-100 dark:hover:bg-slate-700">Profile</a>
                                <a href="#" className="block px-4 py-2 text-sm text-gray-700 dark:text-gray-300 hover:bg-gray-100 dark:hover:bg-slate-700">Settings</a>
                                <hr className="my-2 border-gray-200 dark:border-slate-700" />
                                <a href="#" className="block px-4 py-2 text-sm text-red-600 hover:bg-red-50 dark:hover:bg-red-900/20">Sign out</a>
                            </div>
                        )}
                    </div>
                </div>
            </div>
        </header>
    );
};

export default HeaderComponent;