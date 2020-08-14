package com.ftc11392.sequoia.task;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class InstantTask extends Task {
	private final Runnable toRun;

	public InstantTask(Runnable toRun, Telemetry telemetry) {
		super(telemetry);
		this.toRun = toRun;
		super.running = false;
	}

	@Override
	public void init() {
		toRun.run();
	}
}
