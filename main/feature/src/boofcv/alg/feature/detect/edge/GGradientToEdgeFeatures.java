/*
 * Copyright (c) 2011, Peter Abeles. All Rights Reserved.
 *
 * This file is part of BoofCV (http://www.boofcv.org).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package boofcv.alg.feature.detect.edge;

import boofcv.struct.image.ImageBase;
import boofcv.struct.image.ImageFloat32;
import boofcv.struct.image.ImageSInt16;
import boofcv.struct.image.ImageSInt32;


/**
 * Image type agnostic version of {@link GradientToEdgeFeatures}.
 *
 * @author Peter Abeles
 */
public class GGradientToEdgeFeatures {

	static public <D extends ImageBase>
	void intensityE( D derivX , D derivY , ImageFloat32 intensity )
	{
		if( derivX instanceof ImageFloat32 ) {
			GradientToEdgeFeatures.intensityE((ImageFloat32)derivX,(ImageFloat32)derivY,intensity);
		} else if( derivX instanceof ImageSInt16) {
			GradientToEdgeFeatures.intensityE((ImageSInt16)derivX,(ImageSInt16)derivY,intensity);
		} else if( derivX instanceof ImageSInt32) {
			GradientToEdgeFeatures.intensityE((ImageSInt32)derivX,(ImageSInt32)derivY,intensity);
		} else {
			throw new IllegalArgumentException("Unknown input type");
		}
	}

	static public <D extends ImageBase>
	void intensityAbs( D derivX , D derivY , ImageFloat32 intensity )
	{
		if( derivX instanceof ImageFloat32 ) {
			GradientToEdgeFeatures.intensityAbs((ImageFloat32)derivX,(ImageFloat32)derivY,intensity);
		} else if( derivX instanceof ImageSInt16) {
			GradientToEdgeFeatures.intensityAbs((ImageSInt16)derivX,(ImageSInt16)derivY,intensity);
		} else if( derivX instanceof ImageSInt32) {
			GradientToEdgeFeatures.intensityAbs((ImageSInt32)derivX,(ImageSInt32)derivY,intensity);
		} else {
			throw new IllegalArgumentException("Unknown input type");
		}
	}

	static public <D extends ImageBase>
	void direction( D derivX , D derivY , ImageFloat32 angle )
	{
		if( derivX instanceof ImageFloat32 ) {
			GradientToEdgeFeatures.direction((ImageFloat32)derivX,(ImageFloat32)derivY,angle);
		} else if( derivX instanceof ImageSInt16) {
			GradientToEdgeFeatures.direction((ImageSInt16)derivX,(ImageSInt16)derivY,angle);
		} else if( derivX instanceof ImageSInt32) {
			GradientToEdgeFeatures.direction((ImageSInt32)derivX,(ImageSInt32)derivY,angle);
		} else {
			throw new IllegalArgumentException("Unknown input type");
		}
	}

	static public <D extends ImageBase>
	void direction2( D derivX , D derivY , ImageFloat32 angle )
	{
		if( derivX instanceof ImageFloat32 ) {
			GradientToEdgeFeatures.direction2((ImageFloat32)derivX,(ImageFloat32)derivY,angle);
		} else if( derivX instanceof ImageSInt16) {
			GradientToEdgeFeatures.direction2((ImageSInt16)derivX,(ImageSInt16)derivY,angle);
		} else if( derivX instanceof ImageSInt32) {
			GradientToEdgeFeatures.direction2((ImageSInt32)derivX,(ImageSInt32)derivY,angle);
		} else {
			throw new IllegalArgumentException("Unknown input type");
		}
	}
}