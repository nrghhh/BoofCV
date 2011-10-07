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

package boofcv.abst.feature.detect.line;


import boofcv.abst.feature.detect.extract.FeatureExtractor;
import boofcv.abst.filter.derivative.ImageGradient;
import boofcv.alg.feature.detect.edge.GGradientToEdgeFeatures;
import boofcv.alg.feature.detect.line.HoughTransformLinePolar;
import boofcv.alg.filter.binary.ThresholdImageOps;
import boofcv.core.image.GeneralizedImageOps;
import boofcv.factory.feature.detect.extract.FactoryFeatureExtractor;
import boofcv.struct.FastQueue;
import boofcv.struct.image.ImageBase;
import boofcv.struct.image.ImageFloat32;
import boofcv.struct.image.ImageUInt8;
import georegression.struct.line.LineParametric2D_F32;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Full processing chain for detecting lines using a Hough transform with polar parametrization.
 * </p>
 *
 * <p>
 * USAGE NOTES: Blurring the image prior to processing can often improve performance.
 * Results will not be perfect and to detect all the obvious lines in the image several false
 * positives might be returned.
 * </p>
 *
 * @see boofcv.alg.feature.detect.line.HoughTransformLinePolar
 *
 * @author Peter Abeles
 */
public class DetectLineHoughPolar<I extends ImageBase, D extends ImageBase> implements DetectLine<I> {

	// transform algorithm
	HoughTransformLinePolar alg;

	// computes image gradient
	ImageGradient<I,D> gradient;

	// used to create binary edge image
	float thresholdEdge;

	// image gradient
	D derivX;
	D derivY;

	// edge intensity image
	ImageFloat32 intensity = new ImageFloat32(1,1);

	// detected edge image
	ImageUInt8 binary = new ImageUInt8(1,1);


	public DetectLineHoughPolar(int localMaxRadius,
								int minCounts,
								int numBinsRange ,
								int numBinsAngle ,
								float thresholdEdge,
								ImageGradient<I, D> gradient)
	{
		this.gradient = gradient;
		this.thresholdEdge = thresholdEdge;
		FeatureExtractor extractor = FactoryFeatureExtractor.nonmax(localMaxRadius, minCounts, 0);
		alg = new HoughTransformLinePolar(extractor,numBinsRange,numBinsAngle);
		derivX = GeneralizedImageOps.createImage(gradient.getDerivType(),1,1);
		derivY = GeneralizedImageOps.createImage(gradient.getDerivType(),1,1);
	}

	@Override
	public List<LineParametric2D_F32> detect(I input) {
		derivX.reshape(input.width,input.height);
		derivY.reshape(input.width,input.height);
		intensity.reshape(input.width,input.height);
		binary.reshape(input.width,input.height);

		gradient.process(input,derivX,derivY);
		GGradientToEdgeFeatures.intensityAbs(derivX, derivY, intensity);

		ThresholdImageOps.threshold(intensity, binary, thresholdEdge, false);

		alg.transform(binary);
		FastQueue<LineParametric2D_F32> lines = alg.extractLines();

		List<LineParametric2D_F32> ret = new ArrayList<LineParametric2D_F32>();
		for( int i = 0; i < lines.size; i++ )
			ret.add(lines.get(i));

		return ret;
	}

	public HoughTransformLinePolar getTransform() {
		return alg;
	}

	public D getDerivX() {
		return derivX;
	}

	public D getDerivY() {
		return derivY;
	}

	public ImageFloat32 getEdgeIntensity() {
		return intensity;
	}

	public ImageUInt8 getBinary() {
		return binary;
	}
}
