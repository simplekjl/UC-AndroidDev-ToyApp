package com.example.android.boardingpass;

/*
* Copyright (C) 2016 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.android.boardingpass.databinding.ActivityMainBinding;
import com.example.android.boardingpass.utilities.FakeDataUtils;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        BoardingPassInfo bpi = FakeDataUtils.generateFakeBoardingPassInfo();

        displayBoardingPassInfo(bpi);
    }

    private void displayBoardingPassInfo(BoardingPassInfo info) {

        mBinding.textViewPassengerName.setText(info.passengerName);
        mBinding.textViewOriginAirport.setText(info.originCode);
        mBinding.textViewDestinationAirport.setText(info.destCode);
        mBinding.textViewFlightCode.setText(info.flightCode);
        mBinding.barcode.setImageResource(info.barCodeImageResource);

        SimpleDateFormat formatter = new SimpleDateFormat(getString(R.string.timeFormat), Locale.US);
        String boardingTime = formatter.format(info.boardingTime);
        String departureTime = formatter.format(info.departureTime);
        String arrivalTime = formatter.format(info.arrivalTime);

        mBinding.textViewBoardingTime.setText(boardingTime);
        mBinding.textViewDepartureTime.setText(departureTime);
        mBinding.textViewArrivalTime.setText(arrivalTime);


        long totalMinutesUntilBoarding = info.getMinutesUntilBoarding();
        long hoursUntilBoarding = TimeUnit.MINUTES.toHours(totalMinutesUntilBoarding);
        long minutesLessHoursUntilBoarding = totalMinutesUntilBoarding - TimeUnit.MINUTES.toHours(hoursUntilBoarding);

        String hoursAndMionutesUntilBoarding = getString(R.string.countDownFormat,
                hoursUntilBoarding,minutesLessHoursUntilBoarding);

        mBinding.textViewBoardingInCountdown.setText(hoursAndMionutesUntilBoarding);
        mBinding.textViewTerminal.setText(info.departureTerminal);
        mBinding.textViewGate.setText(info.departureGate);
        mBinding.textViewSeat.setText(info.seatNumber);
    }
}

